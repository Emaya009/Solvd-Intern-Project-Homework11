package petshop.threads;
public class WorkerThread implements Runnable{
    private ConnectionPool connectionPool;
    public WorkerThread(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }
    @Override
    public void run() {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            // Use the connection for some work
            System.out.println("Thread " + Thread.currentThread().getId() + " acquired connection: " + connection);
            Thread.sleep(2000); // Simulating some work
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
                System.out.println("Thread " + Thread.currentThread().getId() + " released connection: " + connection);
            }
        }
    }
}