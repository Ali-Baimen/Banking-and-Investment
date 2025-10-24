package Facade;

import java.util.Map;
import java.util.HashMap;
import java.util.UUID;
import java.util.Objects;
import Account.Account;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class BankingPopup extends JFrame {
    private static class AccountData {
        Account account;
        String password;
        AccountData(Account account, String password) {
            this.account = account;
            this.password = password;
        }
    }

    private final Map<String, AccountData> accounts = new HashMap<>();
    private final BankingFacade facade = new BankingFacade();
    private Account currentAccount;

    private final JTextField nameField = new JTextField(15);
    private final JTextField idField = new JTextField(15);
    private final JPasswordField passwordField = new JPasswordField(15);
    private final JComboBox<String> accountTypeBox =
            new JComboBox<>(new String[]{"Savings (Rewards + Insurance)", "Investment (Tax + Insurance)"});

    private final JTextField amountField = new JTextField(10);
    private final JTextArea logArea = new JTextArea();

    public BankingPopup() {
        setTitle("Banking & Investment System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 520);
        setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        topPanel.setBorder(BorderFactory.createTitledBorder("Customer Info"));
        topPanel.add(new JLabel("Name:"));
        topPanel.add(nameField);
        topPanel.add(new JLabel("Account ID:"));
        topPanel.add(idField);
        topPanel.add(new JLabel("Password:"));
        topPanel.add(passwordField);
        topPanel.add(new JLabel("Account Type:"));
        topPanel.add(accountTypeBox);

        JButton createBtn = new JButton("Create / Login Account");
        createBtn.addActionListener(this::handleCreateOrLogin);

        JPanel createPanel = new JPanel();
        createPanel.add(createBtn);

        JPanel middlePanel = new JPanel(new FlowLayout());
        middlePanel.setBorder(BorderFactory.createTitledBorder("Transactions"));
        middlePanel.add(new JLabel("Amount:"));
        middlePanel.add(amountField);

        JButton depositBtn = new JButton("Deposit");
        JButton withdrawBtn = new JButton("Withdraw");
        JButton closeBtn = new JButton("Close Account");

        depositBtn.addActionListener(e -> performTransaction("deposit"));
        withdrawBtn.addActionListener(e -> performTransaction("withdraw"));
        closeBtn.addActionListener(e -> closeAccount());

        middlePanel.add(depositBtn);
        middlePanel.add(withdrawBtn);
        middlePanel.add(closeBtn);

        logArea.setEditable(false);
        logArea.setLineWrap(true);
        logArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(logArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Activity Log"));

        JPanel content = new JPanel(new BorderLayout(5, 5));
        content.add(topPanel, BorderLayout.NORTH);
        content.add(createPanel, BorderLayout.CENTER);
        content.add(middlePanel, BorderLayout.SOUTH);

        add(content, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    private void handleCreateOrLogin(ActionEvent e) {
        String name = nameField.getText().trim();
        String id = idField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if ((name.isEmpty() && id.isEmpty()) || password.isEmpty()) {
            log("Enter name and password to create or ID and password to log in.");
            return;
        }

        if (!id.isEmpty() && accounts.containsKey(id)) {
            AccountData data = accounts.get(id);
            if (Objects.equals(data.password, password)) {
                currentAccount = data.account;
                log("Logged in to account ID: " + id);
                log("Current balance: $" + currentAccount.getBalance());
            } else {
                log("Incorrect password for account ID: " + id);
            }
            nameField.setText("");
            idField.setText("");
            passwordField.setText("");
            return;
        }

        if (id.isEmpty()) {
            id = "ACC-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        }

        String selection = (String) accountTypeBox.getSelectedItem();
        if (selection == null) return;

        if (selection.startsWith("Savings")) {
            currentAccount = facade.openAccountWithBenefits("savings");
        } else {
            currentAccount = facade.investWithSafetyMode("portfolio");
        }

        accounts.put(id, new AccountData(currentAccount, password));
        log("New account created for " + name + " | ID: " + id);
        log(selection + " successfully initialized.");
        log("Use this ID and password to log in next time: " + id);
        nameField.setText("");
        idField.setText("");
        passwordField.setText("");
    }

    private void performTransaction(String type) {
        if (currentAccount == null) {
            log("No account available. Create or log in first.");
            return;
        }
        try {
            double amt = Double.parseDouble(amountField.getText());
            facade.performTransaction(currentAccount, amt, type);
            log(type + " $" + amt + " | New balance: $" + currentAccount.getBalance());
        } catch (NumberFormatException ex) {
            log("Invalid amount entered.");
        }
    }

    private void closeAccount() {
        if (currentAccount == null) {
            log("No account to close.");
            return;
        }
        facade.closeAccount(currentAccount);
        log("Account closed. Final balance: $" + currentAccount.getBalance());
        currentAccount = null;
    }

    private void log(String message) {
        logArea.append(message + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BankingPopup::new);
    }
}
