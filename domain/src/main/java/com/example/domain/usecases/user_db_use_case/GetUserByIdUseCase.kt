package com.example.domain.usecases.user_db_use_case

import com.example.domain.repository.UserDBRepository
import javax.inject.Inject

class GetUserByIdUseCase @Inject constructor(
    private val userDBRepository: UserDBRepository
) {
    suspend operator fun invoke(id: String) = userDBRepository.getUserById(id)
}