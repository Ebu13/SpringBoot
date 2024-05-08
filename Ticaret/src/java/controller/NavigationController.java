package controller;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;


@Named
@SessionScoped
public class NavigationController implements Serializable{

    private String currentPage = "ProductCategoryMapping.xhtml";

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public void navigateToPage(String pageName) {
        currentPage = pageName;
    }
}
