package de.volkerfaas.kafka.cluster.model;

public class ConsumerConfiguration {

    private final PartitionConfiguration partition;
    private final Long offset;

    public ConsumerConfiguration(PartitionConfiguration partition, Long offset) {
        this.partition = partition;
        this.offset = offset;
    }

    public PartitionConfiguration getPartition() {
        return partition;
    }

    public Long getOffset() {
        return offset;
    }

    @Override
    public String toString() {
        return "ConsumerConfiguration{" +
                "partition=" + partition +
                ", offset=" + offset +
                '}';
    }

}
