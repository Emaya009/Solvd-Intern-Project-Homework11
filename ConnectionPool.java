package petshop.threads;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class ConnectionPool extends CompletableFuture implements CompletionStage {
    private BlockingQueue<Connection> connections;
    public ConnectionPool(int poolSize) {
        connections = new ArrayBlockingQueue<>(poolSize);
        for (int i = 0; i < poolSize; i++) {
            connections.add(new Connection());
        }
    }
    public Connection getConnection() throws InterruptedException {
        return connections.take();
    }
    public void releaseConnection(Connection connection) {
        connections.offer(connection);
    }

    public void releaseConnection(java.sql.Connection connection) {
    }
}