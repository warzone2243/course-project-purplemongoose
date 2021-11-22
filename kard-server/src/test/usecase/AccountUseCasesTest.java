//package usecase;
//
//import database.gateway.AccountGateway;
//import database.gateway.ProfileGateway;
//import entity.accounts.Account;
//import entity.accounts.CorporateAccount;
//import entity.accounts.PersonalAccount;
//import entity.datafiles.Email;
//import entity.datafiles.Name;
//import entity.datafiles.Phone;
//import entity.profiles.Person;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//import java.io.File;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.util.Arrays;
//
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//class AccountUseCasesTest {
//    private AccountUseCases auc;
//
//    /**
//     * Necessary to Query test dbs
//     */
//    private static class aucTest extends AccountUseCases {
//        public aucTest() {
//            this.accountGateway = new AccountGateway() {
//
//                @Override
//                public Connection databaseConnect() {
//                    String mfLocation = "src/test/data/account.db";
//                    File file = new File(mfLocation);
//                    Connection conn = null;
//                    if (file.exists()) {
//                        try {
//                            conn = DriverManager.getConnection(String.format("jdbc:sqlite:%s", mfLocation));
//                        } catch (Exception e) {
//                            System.err.println(e.getMessage());
//                        }
//                    }
//                    else { // if such a db doesn't exist, create one and add a table
//                        try {
//                            conn = DriverManager.getConnection(String.format("jdbc:sqlite:%s", mfLocation));
//                            createAccountTable(conn);
//                        } catch (Exception e) {
//                            System.err.println(e.getMessage());
//                        }
//                    }
//                    return conn; // return a connection for other methods to use
//                }
//            };
//
//            this.profileGateway = new ProfileGateway() {
//                @Override
//                public Connection databaseConnect() {
//                    String mfLocation = "src/test/data/profile.db";
//                    File file = new File(mfLocation);
//                    Connection conn = null;
//                    if (file.exists()) {
//                        try {
//                            conn = DriverManager.getConnection(String.format("jdbc:sqlite:%s", mfLocation));
//                        } catch (Exception e) {
//                            System.err.println(e.getMessage());
//                        }
//                    }
//                    else { // if such a db doesn't exist, create one and add a table
//                        try {
//                            conn = DriverManager.getConnection(String.format("jdbc:sqlite:%s", mfLocation));
//                            createProfileTable(conn);
//                        } catch (Exception e) {
//                            System.err.println(e.getMessage());
//                        }
//                    }
//                    return conn; // return a connection for other methods to use
//                }
//            };
//        }
//    }
//
//
//    private Person adidas;
//
//    @BeforeEach
//    void setUp() {
//        auc = new aucTest();
//
//        Phone phone = new Phone("8347586347");
//        Email email = new Email("adidas@mail.com");
//        adidas = new Person(
//                new Name("Adidas", "Adidas", "Shoe", "Adidas"),
//                phone,
//                email,
//                "adidas"
//        );
//
//        auc.createNewAccount("JohnSmith", "password");
//        auc.accountGateway.insertAccountData("JohnSmith", "password", new PersonalAccount());
//        auc.profileGateway.addProfileData("JohnSmith", new Person(
//                new Name("John", "Smith", "He/Him", "Chancellor"),
//                new Phone("6471234567"),
//                new Email("john.smith@email.com"),
//                "JohnSmith"
//        ));
//        auc.createNewAccount("adidas", "password");
//        auc.accountGateway.insertAccountData("adidas", "password", new CorporateAccount());
//        auc.profileGateway.addProfileData("JohnSmith", adidas);
//    }
//
//    @AfterEach
//    void tearDown() {
//        // reset the database.
//        Connection con = auc.profileGateway.databaseConnect();
//        try {
//            con.prepareStatement("DROP TABLE \"profiles\"").executeUpdate();
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
//        auc.profileGateway.createProfileTable(con);
//        // reset the database.
//        con = auc.accountGateway.databaseConnect();
//        try {
//            con.prepareStatement("DROP TABLE \"accounts\"").executeUpdate();
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
//        auc.accountGateway.createAccountTable(con);
//    }
//
//    @Test
//    @DisplayName("Create a new account")
//    void createNewAccount() {
//        assertTrue(auc.createNewAccount("Joe", "password69"));
//    }
//
//    @Test
//    @DisplayName("Add an account")
//    void addContact() {
//        auc.addContact("JohnSmith", "adidas");
//
//        assertTrue(((Account)auc.accountGateway.getAccountData("JohnSmith")).checkContacts(adidas));
//    }
//
//    @Test
//    @DisplayName("Remove a contact")
//    void removeContact() {
//        auc.addContact("JohnSmith", "adidas");
//        auc.removeContact("JohnSmith", "adidas");
//
//        assertFalse(((Account)auc.accountGateway.getAccountData("JohnSmith")).checkContacts(adidas));
//    }
//
//    @Test
//    @DisplayName("Check for a contact")
//    void checkForContact() {
//        auc.addContact("JohnSmith", "adidas");
//
//        assertTrue(auc.checkForContact("JohnSmith", "adidas"));
//    }
//
//    @Test
//    @DisplayName("Get the contacts")
//    void getContacts() {
//        auc.addContact("JohnSmith", "adidas");
//        Object[] contacts = auc.getContacts("JohnSmith");
//
//        assertTrue(Arrays.asList(contacts).contains(adidas));
//    }
//}