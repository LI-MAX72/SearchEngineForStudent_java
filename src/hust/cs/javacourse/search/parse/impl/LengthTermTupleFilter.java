package hust.cs.javacourse.search.parse.impl;

import hust.cs.javacourse.search.index.AbstractTermTuple;
import hust.cs.javacourse.search.parse.AbstractTermTupleFilter;
import hust.cs.javacourse.search.parse.AbstractTermTupleStream;
import hust.cs.javacourse.search.util.Config;

public class LengthTermTupleFilter extends AbstractTermTupleFilter {
    /**
     * Filter的输入，类型为AbstractTermTupleStream
     */
    protected AbstractTermTupleStream input;

    /**
     * 构造函数
     * @param input：Filter的输入，类型为AbstractTermTupleStream
     */
    public LengthTermTupleFilter(AbstractTermTupleStream input){
        super(input);
    }

    @Override
    public AbstractTermTuple next() {
        AbstractTermTuple lengthfilter = input.next();
        if(lengthfilter==null)
            return null;
        while(lengthfilter.term.getContent().length()< Config.TERM_FILTER_MINLENGTH||
        lengthfilter.term.getContent().length()>Config.TERM_FILTER_MAXLENGTH){
            lengthfilter = input.next();
            if(lengthfilter == null)
                return null;
        }
        return lengthfilter;
    }

    /**
     * 实现父类AbstractTermTupleStream的close方法，关闭流
     */
    @Override
    public void close(){
        input.close();
    }
}
