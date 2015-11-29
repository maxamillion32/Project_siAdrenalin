package projekt.data;


import java.util.ArrayList;
import java.util.List;

import projekt.util.Podatki;

public class TipSportaList {
	
	private List<TipSporta> list;
	
	public TipSportaList() {
		super();
		list = new ArrayList<TipSporta>();
	}

	public List<TipSporta> getList()
    {
        Podatki tmp = new Podatki();
        list = tmp.UstvariListo();
        return list;
	}

	public void setList(ArrayList<TipSporta> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "TipSportaList [" + ", list=" + list + "]";
	}

    public TipSporta getTipSportaW_ID(String id)
    {
        TipSporta ob_TipSporta = new TipSporta();

        for(TipSporta ob_TS : this.getList())
        {
            if(ob_TS.getId().equals(id))
            {
                ob_TipSporta = ob_TS;
            }
        }

        return ob_TipSporta;

    }
	
}
