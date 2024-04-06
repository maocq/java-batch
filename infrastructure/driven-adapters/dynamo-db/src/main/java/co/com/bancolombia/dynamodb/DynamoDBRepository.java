package co.com.bancolombia.dynamodb;

import co.com.bancolombia.model.balance.gateways.BalanceRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@Repository
public class DynamoDBRepository implements BalanceRepository {

    private final DynamoDbTable<BalanceEntity> balanceTable;
    private final ObjectMapper mapper;

    public DynamoDBRepository(DynamoDbEnhancedClient connectionFactory, ObjectMapper mapper) {
        balanceTable = connectionFactory.table("saldos_test1", TableSchema.fromBean(BalanceEntity.class));
        this.mapper = mapper;
    }

    @Override
    public void put(String id) {
        BalanceEntity balanceEntity = new BalanceEntity(id, "S|T|000000000624537", "Name", "Other");
        balanceTable.putItem(balanceEntity);
    }
}
