package hust.cs.javacourse.search.parse.impl;

import hust.cs.javacourse.search.index.AbstractTermTuple;
import hust.cs.javacourse.search.parse.AbstractTermTupleFilter;
import hust.cs.javacourse.search.parse.AbstractTermTupleStream;
import hust.cs.javacourse.search.util.StopWords;
package hust.cs.javacourse.search.util.StopWords;
public class  StopWordTermTupleFilter extends AbstractTermTupleFilter {
    /**
     * Filter的输入，类型为AbstractTermTupleStream
     */
    protected AbstractTermTupleStream input;

    /**
     * 构造函数
     * @param input：Filter的输入，类型为AbstractTermTupleStream
     */
    public StopWordTermTupleFilter(AbstractTermTupleStream input){
        super(input);
    }

    @Override
    public AbstractTermTuple next() {
        AbstractTermTuple stopwordfilter = input.next();
        if(stopwordfilter == null) return null;
        while(!StopWords.STOP_WORDS.contain(stopwordfilter.term.getContent())){
            stopwordfilter  = input.next();
            if(stopwordfilter == null)
                return null;
        }
        return stopwordfilter;
    }

    /**
     * 实现父类AbstractTermTupleStream的close方法，关闭流
     */
    @Override
    public void close(){
        input.close();
    }
}
