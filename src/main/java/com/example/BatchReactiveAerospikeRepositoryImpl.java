package com.example;

import org.springframework.data.aerospike.core.ReactiveAerospikeOperations;
import org.springframework.data.aerospike.core.model.GroupedKeys;
import org.springframework.data.aerospike.repository.support.SimpleReactiveAerospikeRepository;
import org.springframework.data.repository.core.EntityInformation;
import reactor.core.publisher.Flux;

public class BatchReactiveAerospikeRepositoryImpl<T,ID> extends SimpleReactiveAerospikeRepository<T,ID> {

    private EntityInformation<T, ID> entityInformation;
    private ReactiveAerospikeOperations operations;

    public BatchReactiveAerospikeRepositoryImpl(EntityInformation<T, ID> entityInformation,
                                                ReactiveAerospikeOperations operations) {
        super(entityInformation, operations);
        this.entityInformation = entityInformation;
        this.operations = operations;
    }

    @Override
    public Flux<T> findAllById(Iterable<ID> ids) {
        return Flux.fromIterable(ids)
                .buffer(5000)
                .flatMap(batchedIds -> operations.findByIds(GroupedKeys.builder()
                                .entityKeys(entityInformation.getJavaType(), batchedIds)
                        .build()))
                .flatMapIterable(groupedEntities ->
                        groupedEntities.getEntitiesByClass(entityInformation.getJavaType()));
    }
}
