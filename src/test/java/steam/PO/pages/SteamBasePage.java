package steam.PO.pages;

import framework.BasePage;
import framework.elements.BaseElement;
import lombok.Getter;
import steam.PO.commonLogic.Header;
import steam.PO.commonLogic.MainMenu;

@Getter
public class SteamBasePage extends BasePage {

    protected Header header = new Header();
    protected MainMenu mainMenu = new MainMenu();

    public SteamBasePage(BaseElement title, String name) {
        super(title, name);
    }
}