package it.uniroma3.utils;

import javax.faces.bean.ManagedProperty;
import java.util.List;

public abstract class Paginator {

    @ManagedProperty(value = "#{config.itemsPerPage}")
    private int itemsPerPage;

    private String pageTitle;

    private String returnPage = "#";

    private List[] pages;
    private int currentPage;

    public void paginate(List items) {
        paginate(items, this.itemsPerPage);
    }

    public void paginate(List items, int itemsPerPage) {
        this.currentPage = 0;
        int numberOfPages = items.size() / itemsPerPage;
        //assicuriamoci che nessun elemento venga escluso aggiungendo una pagina se necessario
        numberOfPages = ((itemsPerPage * numberOfPages) - items.size() >= 0) ? numberOfPages : numberOfPages + 1;
        this.pages = new List[numberOfPages];
        int index = 0;
        for (int i = 0; i < numberOfPages - 1; i++) {
            this.pages[i] = items.subList(index, index + itemsPerPage);
            index += itemsPerPage;
        }
        // riempiamo l'ultima pagina
        this.pages[numberOfPages - 1] = items.subList(index, items.size());
    }

    public List getPage() {
        return this.pages[this.currentPage];
    }

    public int getCurrentPage() {
        return currentPage + 1;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage - 1;
    }

    public boolean hasNext() {
        return currentPage != pages.length - 1;
    }

    public boolean hasPrev() {
        return currentPage > 0;
    }

    public int getTotalPages() {
        return this.pages.length;
    }

    public List[] getPages() {
        return pages;
    }

    public void setPages(List[] pages) {
        this.pages = pages;
    }


    public String goToPage(int pagenumber) {
        this.currentPage = pagenumber;
        return returnPage;
    }

    public String goToLast() {
        this.currentPage = this.pages.length - 1;
        return returnPage;
    }

    public String goToFirst() {
        this.currentPage = 0;
        return returnPage;
    }

    public String goToNext() {
        if (hasNext())
            ++this.currentPage;
        return returnPage;
    }

    public String goToPrev() {
        if (hasPrev())
            --this.currentPage;
        return returnPage;
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
