
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class clickTest extends BaseTest{
    private MyAccountPage myAccountPage;

    @Override
    @BeforeClass
    public void beforeClass() {
        super.beforeClass();
    }

    @BeforeMethod
    public void beforeMethod() {
    }

    @Test(dataProvider = "DPForAccountCreating", dataProviderClass = User.class)
    public void registerNewAccount(User user) {

}
}
