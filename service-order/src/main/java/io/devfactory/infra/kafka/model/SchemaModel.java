package io.devfactory.infra.kafka.model;

import java.util.List;

public record SchemaModel(String type, List<FieldModel> fields, boolean optional, String name) {

}
