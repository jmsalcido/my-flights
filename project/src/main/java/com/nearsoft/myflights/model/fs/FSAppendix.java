package com.nearsoft.myflights.model.fs;

import java.util.List;

public class FSAppendix {
    private List<FSAirline> airlines;
    private List<FSEquipment> equipments;

    public List<FSAirline> getAirlines() {
        return airlines;
    }

    public void setAirlines(List<FSAirline> airlines) {
        this.airlines = airlines;
    }

    public List<FSEquipment> getEquipments() {
        return equipments;
    }

    public void setEquipments(List<FSEquipment> equipments) {
        this.equipments = equipments;
    }

    @Override
    public String toString() {
        return "FSAppendix [airlines=" + airlines + ", equipments="
                + equipments + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((airlines == null) ? 0 : airlines.hashCode());
        result = prime * result
                + ((equipments == null) ? 0 : equipments.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FSAppendix other = (FSAppendix) obj;
        if (airlines == null) {
            if (other.airlines != null)
                return false;
        } else if (!airlines.equals(other.airlines))
            return false;
        if (equipments == null) {
            if (other.equipments != null)
                return false;
        } else if (!equipments.equals(other.equipments))
            return false;
        return true;
    }

}
