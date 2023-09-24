package com.example.data.repositories.user

import com.example.common.di.IoDispatcher
import com.example.data.mappers.user.toData
import com.example.data.mappers.user.toModel
import com.example.data.models.user.UserModel
import com.example.data.until.synchronizer.NetworkBoundResource
import com.example.data.until.update_time.UpdateTimeHelper
import com.example.localdata.proto.user.LocalUserDataStore
import com.example.localdata.proto.user.UserData
import com.example.network.ApiService
import com.example.network.models.user.UserResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val localUserDataStore: LocalUserDataStore,
    private val canUpdateTimeHelper: UpdateTimeHelper,
    @IoDispatcher
    private val dispatcher: CoroutineDispatcher
):UserRepository {

    override val user:Flow<UserModel?>
        get() = localUserDataStore.userPreferencesFlow.map { it?.toModel() }

    override suspend fun syncCurrentUser(
        force:Boolean
    ) = object : NetworkBoundResource<UserResponse>(dispatcher){
        override suspend fun makeNetworkCall() = apiService.getCurrentUser()

        override suspend fun isUpdateNeeded() = force || canUpdateTimeHelper.canUpdate(UserData::class.java)

        override suspend fun saveLocally(data: UserResponse) {
            localUserDataStore.updateUser(data.toData())
            canUpdateTimeHelper.save(UserData::class.java)
        }
    }.synchronize()

}