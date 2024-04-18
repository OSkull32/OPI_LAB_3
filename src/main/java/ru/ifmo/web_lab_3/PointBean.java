package ru.ifmo.web_lab_3;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import ru.ifmo.web_lab_3.bd.DAOFactory;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Objects;

@Named
@ApplicationScoped
public class PointBean implements Serializable {
    @Inject
    private SelectXBean selectXBean;
    @Inject
    private SelectYBean selectYBean;
    @Inject
    private SelectRBean selectRBean;

    private LinkedList<Point> results;

    public PointBean() {
        super();
        results = new LinkedList<>();
        // fill db with values
        try {
            results = new LinkedList<>(DAOFactory.getInstance().getResultDAO().getAllResults());
        } catch (SQLException ignored) {}
    }

    @Named(value = "resultList")
    public LinkedList<Point> getResults() {
        return results;
    }

    public void setResults(LinkedList<Point> results) {
        this.results = results;
    }

    public void newResult(final double x, final double y, final double r) {
        final Point currentResult = new Point();
        final long startExec = System.nanoTime();
        final boolean result = AreaChecker.isHit(x, y, r);
        final long endExec = System.nanoTime();
        final long executionTime = endExec - startExec;
        currentResult.setX(x);
        currentResult.setY(y);
        currentResult.setR(r);
        currentResult.setStatus(result);
        currentResult.setTime(LocalDateTime.now());
        currentResult.setScriptTime(executionTime);
        try {
            DAOFactory.getInstance().getResultDAO().addNewResult(currentResult);
        } catch (SQLException ignored) {}
        FacesContext.getCurrentInstance().getPartialViewContext().getEvalScripts().add("drawPointXYRRes(" + x + ", " + y + ", " + r + ", " + result + ");");
        results.addFirst(currentResult);
    }

    public void clearResults() {
        results.clear();
        try {
            DAOFactory.getInstance().getResultDAO().clearResults();
            // reload page
            // see https://stackoverflow.com/questions/32947472/how-to-reload-page-when-a-button-is-clicked
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
        } catch (SQLException | IOException ignored) {}
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PointBean)) return false;
        PointBean that = (PointBean) o;
        return Objects.equals(selectXBean, that.selectXBean) && Objects.equals(selectYBean, that.selectYBean) && Objects.equals(selectRBean, that.selectRBean) && Objects.equals(getResults(), that.getResults());
    }

    @Override
    public int hashCode() {
        return Objects.hash(selectXBean, selectYBean, selectRBean, getResults());
    }

    @Override
    public String toString() {
        return "CheckAreaResultsBean{" +
                "selectXBean=" + selectXBean +
                ", selectYBean=" + selectYBean +
                ", selectRBean=" + selectRBean +
                ", results=" + results +
                '}';
    }
}
