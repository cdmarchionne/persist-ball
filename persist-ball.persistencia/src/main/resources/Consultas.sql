--   Consultas utiles para la DB
-- ---------------------------------


-- Resumen de los Partidos Simples Ganados
SELECT equipo1_id,equipo2_id,SUM(Ganados) AS Ganados FROM 
	(SELECT equipo1_id,equipo2_id,COUNT(*) AS Ganados FROM PartidoSimple 
	WHERE golesEquipo1>golesEquipo2
	GROUP BY equipo1_id,equipo2_id
		UNION
	SELECT equipo2_id,equipo1_id,COUNT(*) AS Ganados FROM PartidoSimple 
	WHERE golesEquipo2>golesEquipo1
	GROUP BY equipo2_id,equipo1_id) g
GROUP BY equipo1_id,equipo2_id
ORDER BY equipo1_id,equipo2_id;


-- Resumen de los Partidos Simples Perdidos
SELECT equipo1_id,equipo2_id,SUM(Perdidos) AS Perdidos FROM 
	(SELECT equipo1_id,equipo2_id,COUNT(*) AS Perdidos FROM PartidoSimple 
	WHERE golesEquipo1<golesEquipo2
	GROUP BY equipo1_id,equipo2_id
		UNION
	SELECT equipo2_id,equipo1_id,COUNT(*) AS Perdidos FROM PartidoSimple 
	WHERE golesEquipo2<golesEquipo1
	GROUP BY equipo2_id,equipo1_id) p
GROUP BY equipo1_id,equipo2_id
ORDER BY equipo1_id,equipo2_id;


-- Resumen de los Partidos Simples Empatados
SELECT equipo1_id,equipo2_id,SUM(Empatados) AS Empatados FROM 
	(SELECT equipo1_id,equipo2_id,COUNT(*) AS Empatados FROM PartidoSimple 
	WHERE golesEquipo1=golesEquipo2
	GROUP BY equipo1_id,equipo2_id
		UNION
	SELECT equipo2_id,equipo1_id,COUNT(*) AS Empatados FROM PartidoSimple 
	WHERE golesEquipo2=golesEquipo1
	GROUP BY equipo2_id,equipo1_id) e
GROUP BY equipo1_id,equipo2_id
ORDER BY equipo1_id,equipo2_id;

