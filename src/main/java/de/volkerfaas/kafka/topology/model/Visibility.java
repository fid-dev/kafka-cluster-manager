package de.volkerfaas.kafka.topology.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import de.volkerfaas.kafka.topology.validation.HasValueSchema;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static de.volkerfaas.kafka.topology.model.Visibility.Type.PROTECTED;
import static de.volkerfaas.kafka.topology.model.Visibility.Type.PUBLIC;

@HasValueSchema(visibilities = {PUBLIC, PROTECTED})
public class Visibility implements ItemWithAccessControl {

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum Type {
        PUBLIC("public"),
        PROTECTED("protected"),
        PRIVATE("private");

        private final String value;

        Type(String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    private String prefix;
    private Type type = PUBLIC;
    private final List<AccessControl> consumers;
    private final List<AccessControl> producers;
    private final List<Topic> topics;

    public Visibility() {
        this.consumers = new ArrayList<>();
        this.topics = new ArrayList<>();
        this.producers = new ArrayList<>();
    }

    public Visibility(Type type) {
        this();
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Valid
    @Override
    public List<AccessControl> getConsumers() {
        return consumers;
    }

    @Valid
    public List<Topic> getTopics() {
        return topics;
    }

    //////// JsonIgnore ////////

    @JsonIgnore
    @Override
    public String getPrefix() {
        return prefix;
    }

    @Override
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Valid
    @Override
    public List<AccessControl> getProducers() {
        return producers;
    }

    @JsonIgnore
    @Override
    public String getFullName() {
        return this.prefix + this.type.getValue();
    }

    @Override
    public String toString() {
        return "Visibility{" +
                "prefix='" + prefix + "'," +
                "type='" + type + "'," +
                "fullName='" + getFullName() + "'," +
                (Objects.nonNull(this.consumers) ? "consumers=[" + this.consumers.stream().map(AccessControl::toString).collect(Collectors.joining(",")) + "]," : "") +
                (Objects.nonNull(this.topics) ? "topics=[" + this.topics.stream().map(Topic::toString).collect(Collectors.joining(",")) + "]" : "") +
                '}';
    }
}
