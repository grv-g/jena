/*
 * (c) Copyright 2006, 2007 Hewlett-Packard Development Company, LP
 * All rights reserved.
 * [See end of file]
 */

package com.hp.hpl.jena.sparql.algebra;

import com.hp.hpl.jena.sparql.algebra.op.*;

/** One step in the transformation process */
public class TransformCopy implements Transform
{
    public static final boolean COPY_ALWAYS         = true ;
    public static final boolean COPY_ONLY_ON_CHANGE = false ;
    private boolean alwaysCopy = false ;
    
    public TransformCopy() { this(COPY_ONLY_ON_CHANGE) ; }
    public TransformCopy(boolean alwaysDuplicate)   { this.alwaysCopy = alwaysDuplicate ; }

    public Op transform(OpTable opTable)                            { return xform(opTable) ; }
    public Op transform(OpBGP opBGP)                                { return xform(opBGP) ; }
    public Op transform(OpDatasetNames dsNames)                     { return xform(dsNames) ; }
    public Op transform(OpQuadPattern quadPattern)                  { return xform(quadPattern) ; }

    public Op transform(OpFilter opFilter, Op x)                    { return xform(opFilter, x) ; }
    public Op transform(OpGraph opGraph, Op x)                      { return xform(opGraph, x) ; }
    public Op transform(OpService opService, Op x)                  { return xform(opService, x) ; }
    
    public Op transform(OpJoin opJoin, Op left, Op right)           { return xform(opJoin, left, right) ; }
    
    public Op transform(OpLeftJoin opLeftJoin, Op left, Op right)   { return xform(opLeftJoin, left, right) ; }
    public Op transform(OpDiff opDiff, Op left, Op right)           { return xform(opDiff, left, right) ; }
    public Op transform(OpUnion opUnion, Op left, Op right)         { return xform(opUnion, left, right) ; }
    
    public Op transform(OpExt opExt)                                { return opExt.copy() ; }
    
    public Op transform(OpNull opNull)                              { return opNull.copy() ; }
    public Op transform(OpAssign opAssign)                          { return opAssign.copy() ; }
    
    public Op transform(OpList opList, Op subOp)            { return opList.copy(subOp) ; }
    public Op transform(OpOrder opOrder, Op subOp)          { return opOrder.copy(subOp) ; }
    public Op transform(OpProject opProject, Op subOp)      { return opProject.copy(subOp) ; }
    public Op transform(OpDistinct opDistinct, Op subOp)    { return opDistinct.copy(subOp) ; }
    public Op transform(OpReduced opReduced, Op subOp)      { return opReduced.copy(subOp) ; }
    public Op transform(OpSlice opSlice, Op subOp)          { return opSlice.copy(subOp) ; }
    public Op transform(OpGroupAgg opGroupAgg, Op subOp)    { return opGroupAgg.copy(subOp) ; }

    private Op xform(Op0 op)
    { 
        if ( ! alwaysCopy )
            return op ;
        return op.copy() ;
    }

    private Op xform(Op1 op, Op subOp)
    { 
        if ( ! alwaysCopy && op.getSubOp() == subOp )
            return op ;
        return op.copy(subOp) ;
    }
    
    private Op xform(Op2 op, Op left, Op right)
    {
        if ( ! alwaysCopy && op.getLeft() == left && op.getRight() == right )
            return op ;
        return op.copy(left, right) ;
    }
}

/*
 * (c) Copyright 2006, 2007 Hewlett-Packard Development Company, LP
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. The name of the author may not be used to endorse or promote products
 *    derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */