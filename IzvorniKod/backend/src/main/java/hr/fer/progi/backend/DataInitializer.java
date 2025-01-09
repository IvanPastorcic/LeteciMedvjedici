//package hr.fer.progi.backend;
//
//import hr.fer.progi.backend.model.County;
//import hr.fer.progi.backend.model.Settlement;
//import hr.fer.progi.backend.model.Enum.Region;
//import hr.fer.progi.backend.service.CountyService;
//import hr.fer.progi.backend.service.SettlementService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.event.ApplicationReadyEvent;
//import org.springframework.context.event.EventListener;
//import org.springframework.stereotype.Component;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//
//
//@Component
//public class DataInitializer { // županije su zasad pogrešne lol
//
//	@Autowired
//	private SettlementService settlementService;
//	
//	@Autowired
//	private CountyService countyService;
//
//	@EventListener
//	public void appReady(ApplicationReadyEvent event) {
//		List<County> counties = getCountyData();
//		for (County county : counties) {
//			countyService.createCounty(county);
//		}
//		List<Settlement> settlements = readSettlementsFromCSV("naselja.csv");
//		for (Settlement settlement : settlements) {
//			settlementService.createSettlement(settlement);
//		}
//	}
//
//	private List<County> getCountyData() {
//	    List<County> counties = new ArrayList<>();
//	    counties.add(new County("Zagrebačka", Region.CENTRAL));
//	    counties.add(new County("Krapinsko-zagorska", Region.CENTRAL));
//	    counties.add(new County("Sisačko-moslavačka županija", Region.CENTRAL));
//	    counties.add(new County("Karlovačka", Region.CENTRAL));
//	    counties.add(new County("Varaždinska", Region.CENTRAL));
//	    counties.add(new County("Koprivničko-križevačka", Region.CENTRAL));
//	    counties.add(new County("Bjelovarsko-bilogorska", Region.CENTRAL));
//	    counties.add(new County("Primorsko-goranska", Region.NORTH_SEASIDE));
//	    counties.add(new County("Ličko-senjska", Region.GORSKI_KOTAR));
//	    counties.add(new County("Virovitičko-podravska", Region.EAST));
//	    counties.add(new County("Požeško-slavonska", Region.EAST));
//	    counties.add(new County("Brodsko-posavska", Region.EAST));
//	    counties.add(new County("Zadarska", Region.SOUTH_SEASIDE));    
//	    counties.add(new County("Osječko-baranjska", Region.EAST));
//	    counties.add(new County("Šibensko-kninska", Region.SOUTH_SEASIDE));
//	    counties.add(new County("Vukovarsko-srijemska", Region.EAST));
//	    counties.add(new County("Splitsko-dalmatinska", Region.SOUTH_SEASIDE));
//	    counties.add(new County("Istarska", Region.NORTH_SEASIDE));
//	    counties.add(new County("Dubrovačko-neretvanska", Region.SOUTH_SEASIDE));
//	    counties.add(new County("Međimurska", Region.CENTRAL));
//	    counties.add(new County("Grad Zagreb", Region.CENTRAL));
//	    return counties;
//	}
//
//
//	private List<Settlement> readSettlementsFromCSV(String fileName) {
//		List<Settlement> settlements = new ArrayList<>();
//		try (BufferedReader br = new BufferedReader(new InputStreamReader(
//				Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(fileName))))) {
//			Long id = 0L;
//			String line;
//			while ((line = br.readLine()) != null) {
//				String[] data = line.split(",");
//				id++;
//				Long countyId = Long.parseLong(data[1]); 
//				String settlementName = data[4]; 
//
//				County county = countyService.findById(countyId);
//				Settlement settlement = new Settlement(settlementName, county);
//
//				settlements.add(settlement);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return settlements;
//	}
//}
