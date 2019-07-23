package hmmmm;

import org.openqa.selenium.WebElement;

abstract class AbstractPage {
}

interface IGetAttribute{
    default String getTextBoxValueAttributeStatic
            (WebElement TextBox) {
        return TextBox.getAttribute("value");
    }
}