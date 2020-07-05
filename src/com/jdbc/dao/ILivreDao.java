package com.jdbc.dao;

import com.librairie.model.livre.Livre;

public interface ILivreDao extends Dao<Livre> {

	void updateQuantitee(Livre pObject);
	
	Livre selectLivreByRef(int pIndex);

}
