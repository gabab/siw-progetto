package it.uniroma3.controller;

import java.util.List;

public abstract class Paginator {

    private List[] pages;

    private int currentPage;

    public List getPage() {
        return pages[currentPage];
    }

    public void paginate(List items, int itemsPerPage) {
        int numberOfPages = items.size() / itemsPerPage;
        //assicuriamoci che nessun elemento venga escluso aggiungendo una pagina se necessario
        numberOfPages = ((itemsPerPage * numberOfPages) - items.size() >= 0) ? numberOfPages : numberOfPages + 1;
        pages = new List[numberOfPages];
        int index = 0;
        for (int i = 0; i < numberOfPages - 1; i++) {
            pages[i] = items.subList(index, index + itemsPerPage);
            index += itemsPerPage;
        }
        // riempiamo l'ultima pagina
        pages[numberOfPages - 1] = items.subList(index, items.size());
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public boolean hasNext() {
        return currentPage + 1 <= pages.length;
    }

    public boolean hasPrev() {
        return currentPage > 0;
    }


    public boolean isLastPage() {
        return currentPage == pages.length -1;
    }

    public boolean isFirstPage() {
        return currentPage == 0;
    }

    public int getTotalPages(){
        return pages.length;
    }

    public List[] getPages() {
        return pages;
    }

    public void setPages(List[] pages) {
        this.pages = pages;
    }


    public String goToPage(int pagenumber) {
        currentPage = pagenumber;
        return "#";
    }

    public String goToLast() {
        currentPage = pages.length - 1;
        return "#";
    }

    public String goToFirst() {
        currentPage = 0;
        return "#";
    }


    public void reset(){
        currentPage = 0;
    }

    public String goToNext() {
        if (hasNext())
            currentPage++;
        return "#";
    }

    public String goToPrev() {
        if (hasPrev())
            currentPage--;
        return "#";
    }
}
