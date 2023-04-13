import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {

    private String newDateString;

    @BeforeEach
    public void newDate() {

        LocalDate currentDate = LocalDate.now();
        LocalDate newDate = currentDate.plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        newDateString = newDate.format(formatter);
    }

    @Test
    void shouldOrderDeliveryCardWithValidValues() {

        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Петропавловск-Камчатский");
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(newDateString);
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        $("[data-test-id='phone'] input").setValue("+79999999999");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $(".notification__content").shouldHave(Condition.text(newDateString), Duration.ofSeconds(15));

    }

}
