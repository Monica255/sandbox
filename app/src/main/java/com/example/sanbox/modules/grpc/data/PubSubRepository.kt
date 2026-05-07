package com.example.sanbox.modules.grpc.data

import io.grpc.ManagedChannelBuilder
import pubsub.PubSubGrpcKt
import pubsub.Pubsub

class PubSubRepository {

    private val channel = ManagedChannelBuilder
        .forAddress("10.0.2.2", 50051)
        .usePlaintext()
        .build()

    private val stub = PubSubGrpcKt.PubSubCoroutineStub(channel)

    /**
     * Subscribe to a topic. Events are emitted as a Flow.
     */
    fun subscribe(topic: String) = stub.subscribe(
        Pubsub.SubscribeRequest.newBuilder()
            .setTopic(topic)
            .build()
    )

    /**
     * Publish a message to a topic.
     */
    suspend fun publish(topic: String, data: String): Pubsub.PublishResponse {
        return stub.publish(
            Pubsub.PublishRequest.newBuilder()
                .setTopic(topic)
                .setData(data)
                .build()
        )
    }

    fun shutdown() {
        channel.shutdown()
    }
}