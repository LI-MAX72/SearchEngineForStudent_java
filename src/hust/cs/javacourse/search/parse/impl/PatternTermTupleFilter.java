package hust.cs.javacourse.search.parse.impl;

import hust.cs.javacourse.search.index.AbstractTermTuple;
import hust.cs.javacourse.search.parse.AbstractTermTupleFilter;
import hust.cs.javacourse.search.parse.AbstractTermTupleStream;
import hust.cs.javacourse.search.util.Config;

public class PatternTermTupleFilter extends AbstractTermTupleFilter {
    /**
     * Filter的输入，类型为AbstractTermTupleStream
     */
    protected AbstractTermTupleStream input;
    String pattern = Config.TERM_FILTER_PATTERN;
    /**
     * 构造函数
     * @param input：Filter的输入，类型为AbstractTermTupleStream
     */
    public PatternTermTupleFilter(AbstractTermTupleStream input){
        super(input);
    }

    @Override
    public AbstractTermTuple next() {
        AbstractTermTuple patternfilter = input.next();
        if(patternfilter == null) return null;
        while (!patternfilter.term.getContent().matches(pattern))
        {
            patternfilter = input.next();
            if(patternfilter == null)
                return null;
        }
        return patternfilter;
    }

    /**
     * 实现父类AbstractTermTupleStream的close方法，关闭流
     */
    @Override
    public void close(){
        input.close();
    }
}
