package projekt.data;

import java.util.ArrayList;

import projekt.siadrenalin.MyApplication;

public class SportList {
	private ArrayList<Sport> list;
    MyApplication app;

	public SportList() {
		super();

		list = new ArrayList<Sport>();
	}
	public ArrayList<Sport> getList(){


		return list;
	}

	public void setList(ArrayList<Sport> list) {
		this.list = list;
	}

    public ArrayList<Sport> getFavs(String priljubljene)
    {
        String[] priljubljene_polje = priljubljene.split(",");

        //ArrayList<TipSporta> listTP = new ArrayList<TipSporta>();
        TipSportaList tsl = new TipSportaList();
        //tsl.getList();

        ArrayList<Sport> tmp = new ArrayList<Sport>();

        for(int i=0; i<tsl.getList().size(); i++)
        {
            for(int j=0; j<tsl.getList().get(i).getData().size(); j++)
            {
                for(int z=0; z<priljubljene_polje.length; z++)
                {
                    if(tsl.getList().get(i).getData().get(j).getId() == priljubljene_polje[z])
                    {
                        tmp.add(tsl.getList().get(i).getData().get(j));
                    }
                }
            }
        }
        return tmp;
    }
	@Override
	public String toString() {
		return "SportList [" + list + "]";
	}
}
