package com.gdn.rentalan.ui.user.model

import com.gdn.rentalan.api.response.User

interface UserMapper {
    companion object {
        fun mapToUserDetailUiModel(item: User): UserDetailUiModel {
            return UserDetailUiModel(
                    item.address,
                    item.gender,
                    item.city,
                    item.facePhotoPath,
                    item.birthDate,
                    item.nik,
                    item.phoneNumber,
                    item.province,
                    item.ktpPhotoPath,
                    item.id,
                    item.sureName,
                    item.email,
                    item.selfPhotoPath,
                    item.username,
                    item.status
            )
        }
    }
}