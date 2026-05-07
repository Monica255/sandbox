package com.example.server
//
//class UserServiceImpl : UserServiceGrpcKt.UserServiceCoroutineImplBase() {
//
//    override suspend fun getUser(request: UserRequest): UserResponse {
//        return UserResponse.newBuilder()
//            .setId(request.id)
//            .setName("Monica") // hardcoded
//            .build()
//    }
//
//    override fun streamUsers(request: UserRequest) = flow {
//        var count = 1
//        while (true) {
//            emit(
//                UserResponse.newBuilder()
//                    .setId(count)
//                    .setName("User $count")
//                    .build()
//            )
//            count++
//            delay(2000) // simulate updates
//        }
//    }
//}