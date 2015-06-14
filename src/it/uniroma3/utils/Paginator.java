package it.uniroma3.utils;

import javax.faces.bean.ManagedProperty;
import java.util.List;

public abstract class Paginator {

    @ManagedProperty(value = "#{config.itemsPerPage}")
    private int itemsPerPage;

    private String pageTitle;

    private List[] pages;

    private int currentPage;

    public void paginate(List items) {
        this.currentPage = 0;
        int numberOfPages = items.size() / this.itemsPerPage;
        //assicuriamoci che nessun elemento venga escluso aggiungendo una pagina se necessario
        numberOfPages = ((this.itemsPerPage * numberOfPages) - items.size() >= 0) ? numberOfPages : numberOfPages + 1;
        this.pages = new List[numberOfPages];
        int index = 0;
        for (int i = 0; i < numberOfPages - 1; i++) {
            this.pages[i] = items.subList(index, index + this.itemsPerPage);
            index += this.itemsPerPage;
        }
        // riempiamo l'ultima pagina
        this.pages[numberOfPages - 1] = items.subList(index, items.size());
    }


    public List getPage() {
        return this.pages[this.currentPage];
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public boolean hasNext() {
        return currentPage != pages.length - 1;
    }

    public boolean hasPrev() {
        return currentPage > 0;
    }

    public int getTotalPages() {
        return pages.length;
    }

    public List[] getPages() {
        return pages;
    }

    public void setPages(List[] pages) {
        this.pages = pages;
    }


    public String goToPage(int pagenumber) {
        this.currentPage = pagenumber;
        return "#";
    }

    public String goToLast() {
        this.currentPage = this.pages.length - 1;
        return "#";
    }

    public String goToFirst() {
        this.currentPage = 0;
        return "#";
    }

    public String goToNext() {
        if (hasNext())
            this.currentPage++;
        return "#";
    }

    public String goToPrev() {
        if (hasPrev())
            this.currentPage--;
        return "#";
    }

    public void setItemsPerPage(int itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    public String getPageTitle() {
        return this.pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }
}
