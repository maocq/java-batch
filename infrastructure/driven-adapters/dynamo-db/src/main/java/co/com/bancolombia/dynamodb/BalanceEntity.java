package co.com.bancolombia.dynamodb;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

@DynamoDbBean
public class BalanceEntity {

    private String id;
    private String type;
    private String name;
    private String other;

    public BalanceEntity() {
    }

    public BalanceEntity(String id, String email, String name, String other) {
        this.id = id;
        this.type = email;
        this.name = name;
        this.other = other;
    }

    @DynamoDbPartitionKey
    @DynamoDbAttribute("NUMERO_DE_CUENTA")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDbSortKey
    @DynamoDbAttribute("TIPO_CUENTA_IDENTIFICACION")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
