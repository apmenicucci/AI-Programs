import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.sql.*;

public class AdvancedBankingSystem extends Application {
    private Connection connection;
    private Statement statement;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        connectToDatabase();

        primaryStage.setTitle("Advanced Banking System");

        TabPane tabPane = new TabPane();
        tabPane.getTabs().add(createAccountTab());
        tabPane.getTabs().add(createTransactionsTab());

        StackPane stackPane = new StackPane(tabPane);
        Scene scene = new Scene(stackPane, 400, 300);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Tab createAccountTab() {
        Tab tab = new Tab("Create Account");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);

        TextField nameField = new TextField();
        TextField balanceField = new TextField();
        Button createButton = new Button("Create Account");

        createButton.setOnAction(e -> {
            String name = nameField.getText();
            double balance = Double.parseDouble(balanceField.getText());

            createAccount(name, balance);

            nameField.clear();
            balanceField.clear();
        });

        grid.add(new Label("Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Initial Balance:"), 0, 1);
        grid.add(balanceField, 1, 1);
        grid.add(createButton, 1, 2);

        tab.setContent(grid);
        return tab;
    }

    private Tab createTransactionsTab() {
        Tab tab = new Tab("Transactions");

        ListView<String> accountList = new ListView<>();
        accountList.setPrefWidth(200);
        updateAccountList(accountList);

        TextField amountField = new TextField();
        Button depositButton = new Button("Deposit");
        Button withdrawButton = new Button("Withdraw");
        Label balanceLabel = new Label();

        depositButton.setOnAction(e -> {
            String selectedAccount = accountList.getSelectionModel().getSelectedItem();
            double amount = Double.parseDouble(amountField.getText());
            deposit(selectedAccount, amount);
            amountField.clear();
            updateBalanceLabel(selectedAccount, balanceLabel);
        });

        withdrawButton.setOnAction(e -> {
            String selectedAccount = accountList.getSelectionModel().getSelectedItem();
            double amount = Double.parseDouble(amountField.getText());
            withdraw(selectedAccount, amount);
            amountField.clear();
            updateBalanceLabel(selectedAccount, balanceLabel);
        });

        VBox vbox = new VBox(accountList, amountField, depositButton, withdrawButton, balanceLabel);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);

        tab.setContent(vbox);
        return tab;
    }

    private void connectToDatabase() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:bank.db");
            statement = connection.createStatement();

            statement.execute("CREATE TABLE IF NOT EXISTS accounts " +
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, balance REAL)");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createAccount(String name, double balance) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO accounts (name, balance) VALUES (?, ?)");
            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, balance);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateAccountList(ListView<String> accountList) {
        try {
            ResultSet resultSet = statement.executeQuery("SELECT name FROM accounts");
            while (resultSet.next()) {
                accountList.getItems().add(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deposit(String accountName, double amount) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE accounts SET balance = balance + ? WHERE name = ?");
            preparedStatement.setDouble(1, amount);
            preparedStatement.setString(2, accountName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void withdraw(String accountName, double amount) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE accounts SET balance = balance - ? WHERE name = ?");
            preparedStatement.setDouble(1, amount);
            preparedStatement.setString(2, accountName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateBalanceLabel(String accountName, Label balanceLabel) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT balance FROM accounts WHERE name = ?");
            preparedStatement.setString(1, accountName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                double balance = resultSet.getDouble("balance");
                balanceLabel.setText("Balance: $" + balance);
            } else {
                balanceLabel.setText("");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        try {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
