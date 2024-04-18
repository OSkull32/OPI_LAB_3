package ru.ifmo.web_lab_3;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.Objects;

@Named
@SessionScoped
public class SelectXBean implements Serializable {
    private boolean selectedM4;
    private boolean selectedM3;
    private boolean selectedM2;
    private boolean selectedM1;
    private boolean selected0;
    private boolean selected1;
    private boolean selected2;
    private boolean selected3;
    private boolean selected4;
    private SelectX lastSelected;

    public SelectXBean() {
        lastSelected = SelectX.UNSELECTED;
    }
    public boolean isSelectedM4() {
        return selectedM4;
    }

    public void setSelectedM4(boolean selectedM4) {
        this.selectedM4 = selectedM4;
    }
    public boolean isSelectedM3() {
        return selectedM3;
    }

    public void setSelectedM3(boolean selectedM3) {
        this.selectedM3 = selectedM3;
    }

    public boolean isSelectedM2() {
        return selectedM2;
    }

    public void setSelectedM2(boolean selectedM2) {
        this.selectedM2 = selectedM2;
    }

    public boolean isSelectedM1() {
        return selectedM1;
    }

    public void setSelectedM1(boolean selectedM1) {
        this.selectedM1 = selectedM1;
    }

    public boolean isSelected0() {
        return selected0;
    }

    public void setSelected0(boolean selected0) {
        this.selected0 = selected0;
    }

    public boolean isSelected1() {
        return selected1;
    }

    public void setSelected1(boolean selected1) {
        this.selected1 = selected1;
    }
    public boolean isSelected2() {
        return selected2;
    }

    public void setSelected2(boolean selected2) {
        this.selected2 = selected2;
    }
    public boolean isSelected3() {
        return selected3;
    }

    public void setSelected3(boolean selected3) {
        this.selected3 = selected3;
    }
    public boolean isSelected4() {
        return selected4;
    }

    public void setSelected4(boolean selected4) {
        this.selected4 = selected4;
    }

    public SelectX getLastSelected() {
        return lastSelected;
    }

    public double getValue() {
        return lastSelected.getValue();
    }

    public void checkboxValueChanged() {
        if (isSelectedM4() && lastSelected.ordinal() == 0) setSelectedM4(false);
        if (isSelectedM3() && lastSelected.ordinal() == 1) setSelectedM3(false);
        if (isSelectedM2() && lastSelected.ordinal() == 2) setSelectedM2(false);
        if (isSelectedM1() && lastSelected.ordinal() == 3) setSelectedM1(false);
        if (isSelected0() && lastSelected.ordinal() == 4) setSelected0(false);
        if (isSelected1() && lastSelected.ordinal() == 5) setSelected1(false);
        if (isSelected2() && lastSelected.ordinal() == 6) setSelected2(false);
        if (isSelected3() && lastSelected.ordinal() == 7) setSelected3(false);
        if (isSelected4() && lastSelected.ordinal() == 8) setSelected4(false);

        if (isSelectedM4()) lastSelected = SelectX.MINUS4;
        else if (isSelectedM3()) lastSelected = SelectX.MINUS3;
        else if (isSelectedM2()) lastSelected = SelectX.MINUS2;
        else if (isSelectedM1()) lastSelected = SelectX.MINUS1;
        else if (isSelected0()) lastSelected = SelectX.PLUS0;
        else if (isSelected1()) lastSelected = SelectX.PLUS1;
        else if (isSelected2()) lastSelected = SelectX.PLUS2;
        else if (isSelected3()) lastSelected = SelectX.PLUS3;
        else if (isSelected4()) lastSelected = SelectX.PLUS4;
        else lastSelected = SelectX.UNSELECTED;
    }

    public void validateSelectX(FacesContext context, UIComponent component, Object value) {
        if (lastSelected.getValue() == null) {
            FacesMessage message = new FacesMessage("Please, select at least one checkbox!");
            throw new ValidatorException(message);
        }
    }

    @Override
    public String toString() {
        return "SelectXBean{" +
                "selectedM4=" + selectedM4 +
                ", selectedM3=" + selectedM3 +
                ", selectedM2=" + selectedM2 +
                ", selectedM1=" + selectedM1 +
                ", selected0=" + selected0 +
                ", selected1=" + selected1 +
                ", selected2=" + selected2 +
                ", selected3=" + selected3 +
                ", selected4=" + selected4 +
                ", lastSelected=" + lastSelected +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SelectXBean that)) return false;
        return isSelectedM4() == that.isSelectedM4() && isSelectedM3() == that.isSelectedM3() && isSelectedM2() == that.isSelectedM2() && isSelectedM1() == that.isSelectedM1() && isSelected0() == that.isSelected0() && isSelected1() == that.isSelected1() && isSelected2() == that.isSelected2() && isSelected3() == that.isSelected3() && isSelected4() == that.isSelected4() && getLastSelected() == that.getLastSelected();
    }

    @Override
    public int hashCode() {
        return Objects.hash(isSelectedM4(), isSelectedM3(), isSelectedM2(), isSelectedM1(), isSelected0(), isSelected1(), isSelected2(), isSelected3(), isSelected4(), getLastSelected());
    }
}