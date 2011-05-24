package ar.edu.unq.tpi.persit.ball.persistencia.logger;

import java.util.ArrayList;
import java.util.List;

import javassist.compiler.ast.Pair;
import ar.edu.unq.tpi.persist.ball.domain.bean.Equipo;
import ar.edu.unq.tpi.persist.ball.domain.utils.Par;

public class RankingPartidoCopa {
	private List<Par<Equipo, Number>> resultList = new ArrayList<Par<Equipo, Number>>();
	
	public RankingPartidoCopa(List<Object[]> list){
		for (Object[] o :list){
			resultList.add(new Par<Equipo, Number>((Equipo)o[0],(Number)o[1]));
		}
	}

	public List<Par<Equipo, Number>> getResultList() {
		return resultList;
	}

	public void setResultList(List<Par<Equipo, Number>> resultList) {
		this.resultList = resultList;
	}
	
	
}
