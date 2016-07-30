package test.com.coreyshort;

import com.coreyshort.MergeSortAndCompare;
import com.coreyshort.ReturningValues;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** 
* MergeSortAndCompare Tester. 
* 
* @author Corey Short
* @since <pre>Aug 3, 2015</pre> 
* @version 1.0 
*/ 
public class MergeSortAndCompareTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: sort(List<Integer> list, Integer targetVal) 
* 
*/ 
@Test
public void testSort() throws Exception { 
}


/** 
* 
* Method: merge(ReturningValues left, ReturningValues right, Integer targetVal) 
* 
*/ 
@Test
public void testMerge() throws Exception {
   try {
      Method method = MergeSortAndCompare.class.getMethod("merge", ReturningValues.class, ReturningValues.class, Integer.class);
      method.setAccessible(true);
      method.invoke(ReturningValues.class, new ArrayList<Integer>(Arrays.asList(5,4)));
   } catch(NoSuchMethodException e) {
   } catch(IllegalAccessException e) {
   } catch(InvocationTargetException e) {
   }
}

/** 
* 
* Method: compare(List<Integer> left, List<Integer> right, Integer targetVal) 
* 
*/ 
@Test
public void testCompare() throws Exception { 
/*
try { 
   Method method = MergeSortAndCompare.getClass().getMethod("compare", List<Integer>.class, List<Integer>.class, Integer.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

} 
