import static junit.framework.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class PojoConverterTest {

	private List<Pojo> input;
	
	@Before
	public void buildInput() {
		Pojo obj1 = new Pojo("1", SomeCode.ARTICLE);
		Pojo obj2 = new Pojo("2", SomeCode.PROFILE);
		Pojo obj3 = new Pojo("3", SomeCode.ARTICLE);
		input = new ArrayList<>();
		input.add(obj1);
		input.add(obj2);
		input.add(obj3);
	}

	@Test
	public void convertToListOfIdsTest(){
		String resultString = new PojoListToIdsCollectionConverter().convertToListOfIds(input);
		assertEquals(resultString, "collect ids to list \n[1, 2, 3]");
	}
	
	@Test
	public void convertToMapOfCodesandIdsTest(){
		Map<SomeCode, Set<String>> result = new PojoListToIdsCollectionConverter().convertToMapOfCodesAndIds(input);
		testMap(result);
	}

	private void testMap(Map<SomeCode, Set<String>> result) {
		assertEquals(2, result.size());
		assertEquals(1, result.get(SomeCode.PROFILE).size());
		assertEquals("2", result.get(SomeCode.PROFILE).iterator().next());
		assertEquals(2, result.get(SomeCode.ARTICLE).size());
		Iterator<String> idsIterator = result.get(SomeCode.ARTICLE).iterator();
		assertEquals("1", idsIterator.next());
		assertEquals("3", idsIterator.next());
	}

	@Test
	public void convertToMapOfCodesandIdsUsingLambdasTest(){
		Map<SomeCode, Set<String>> result = new PojoListToIdsCollectionConverter().convertToMapOfCodesAndIdsUsingLambdas(input);
		testMap(result);
	}

	@Test
	public void convertToMapOfCodesandIdsUsingComputeIfAbsentTest(){
		Map<SomeCode, Set<String>> result = new PojoListToIdsCollectionConverter().convertToMapOfCodesAndIdsUsingComputeIfAbsent(input);
		testMap(result);
	}
}
