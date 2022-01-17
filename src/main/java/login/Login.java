package login;
import util.InputFromCMD;

import java.util.ArrayList;
import java.util.List;

public class Login {
    private boolean isLogin = false;
    private boolean isAdmin = false;
    private List<Account> account = new ArrayList<Account>();

    class Account {
        String username;
        String password;
        boolean admin;

        public Account(String username, String password, boolean admin) {
            this.username = username;
            this.password = password;
            this.admin = admin;
        }
    }

    public Login() {
        account.add(new Account("admin", "12345678", true));
        account.add(new Account("user", "12345678", false));
        account.add(new Account("adminnumber1", "12345678", true));
        account.add(new Account("adminnumber2", "87654321", true));
        account.add(new Account("chumeocute", "12345678", false));
        account.add(new Account("embetapdi", "12345678", false));

    }

    public void login() {
        System.out.println("LOGIN");
        while (!isLogin()) {
            boolean check = true;
            while (check) {
                String username = InputFromCMD.inputString("Username: ", "", "");
                for (Account x : account) {
                    if (x.username.equals(username)) {
                        int count = 0;
                        while (count < 3) {
                            String password = InputFromCMD.inputString("Password: ", "", "");
                            if (password.equals(x.password)) {
                                System.out.println("Login successfully!");
                                isLogin = true;
                                isAdmin = x.admin;
                                check = false;
                                break;
                            } else {
                                if (count == 2) {
                                    System.out.println("Incorrect password to many time!");
                                    System.exit(0);
                                }
                                System.out.println("Password incorrect, please try again!");
                                count++;
                            }
                        }

                    }
                }
                if (check) {
                    System.out.println("Username not found, please try again!");
                }
            }

        }

        String password;
    }

    public boolean isLogin() {
        return isLogin;
    };

    public boolean isAdmin() {
        return isAdmin;
    }



}
