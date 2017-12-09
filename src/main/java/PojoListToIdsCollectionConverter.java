import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class PojoListToIdsCollectionConverter {

	//simplest streams example
	String convertToListOfIds(List<Pojo> input) {
		List<String> idsList = input.stream()
		.map(el->el.getId())
		.collect(Collectors.toList());
		System.out.println(idsList);
		return "collect ids to list \n" + 
				idsList;
	}

	Map<SomeCode, Set<String>> convertToMapOfCodesAndIds(List<Pojo> input) {
		Map<SomeCode, Set<String>> result = new HashMap<>();
		Consumer<Pojo> addToMap = pojo -> {
			Set<String> idsForCode = result.get(pojo.getCode());
			if (idsForCode == null ){
				idsForCode = new HashSet<>();
				result.put(pojo.getCode(), idsForCode ) ;
			}
			idsForCode.add(pojo.getId());
		};
		input.forEach( addToMap );
		System.out.println(result);
		return result;
	}

	Map<SomeCode, Set<String>> convertToMapOfCodesAndIdsUsingLambdas(List<Pojo> input) {
		Map<SomeCode, Set<String>> result = new HashMap<>();
		Consumer<Pojo> addToMap = pojo -> {
			Set<String> idsForCode = result.get(pojo.getCode());
			if (idsForCode == null ){
				idsForCode = new HashSet<>();
				result.put(pojo.getCode(), idsForCode ) ;
			}
			idsForCode.add(pojo.getId());
		};
		input.forEach( addToMap );
		System.out.println(result);
		return result;
	}

	Map<SomeCode, Set<String>> convertToMapOfCodesAndIdsUsingComputeIfAbsent(List<Pojo> input) {
		Map<SomeCode, Set<String>> result = new HashMap<>();
		
		input.forEach( pojo ->
			result.computeIfAbsent(pojo.getCode(), idsSet -> new HashSet<String>()).add(pojo.getId())
		);
		
		System.out.println(result);
		return result;
	}
	
}
