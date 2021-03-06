package restassuredtest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.hamcrest.Matchers.not;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;
public class TestWithHamcrest {
@Test
public void test1() {
	
	assertThat(4,equalTo(4));
	assertThat("abc",is("abc"));
	assertThat("abc",not( equalTo("efg") ));
	
	   assertThat("abc", containsString("a") );
	    
	    assertThat("aBC", equalToIgnoringCase("abc") );

	    
	    assertThat(5, greaterThan(4) );
	    assertThat(5, lessThan(6) );
	    assertThat(5, lessThanOrEqualTo(4) );
	    
	    
	    
	    List<Integer> lst = Arrays.asList(2,3,4,5);
	    
	    //import static org.hamcrest.collection.IsCollectionWithSize.hasSize ;
	    assertThat(lst, hasSize(4) );
	    
	    assertThat(lst, contains(2,3,4) );
	    
	    assertThat(lst, everyItem( greaterThan(1) ) );
	    
}

}
