package com.gdn.rentalan.ui.user.model

import com.gdn.rentalan.api.response.User

interface UserMapper {
    companion object {
        fun mapToUserDetailUiModel(item: User): UserDetailUiModel {
            return UserDetailUiModel(
                    address = item.address,
                    gender = item.gender,
                    city = item.city,
                    birthDate = item.birthDate,
                    nik = item.nik,
                    phoneNumber = item.phoneNumber,
                    province = item.province,
                    ktpPhotoPath = item.ktpPhotoPath,
                    id = item.id,
                    sureName = item.sureName,
                    email = item.email,
                    selfPhotoPath = item.selfPhotoPath,
                    username = item.username,
                    status = item.status
            )
        }
    }
}