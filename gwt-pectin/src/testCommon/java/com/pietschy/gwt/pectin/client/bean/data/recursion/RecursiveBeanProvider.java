/*
 * Copyright 2009 Andrew Pietsch 
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you 
 * may not use this file except in compliance with the License. You may 
 * obtain a copy of the License at 
 *      
 *      http://www.apache.org/licenses/LICENSE-2.0 
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or 
 * implied. See the License for the specific language governing permissions 
 * and limitations under the License. 
 */

package com.pietschy.gwt.pectin.client.bean.data.recursion;

import com.pietschy.gwt.pectin.client.bean.BeanModelProvider;
import com.pietschy.gwt.pectin.client.bean.LimitPropertyDepth;
import com.pietschy.gwt.pectin.client.bean.NestedTypes;

/**
 * This would normally be a static inner class but we I'm putting it in a different
 * package so our generated provider will fail if it doesn't have all it's imports
 * correctly defined.
*/
@LimitPropertyDepth(4)
@NestedTypes({RecursiveBeanOne.class, RecursiveBeanTwo.class})
public abstract class RecursiveBeanProvider extends BeanModelProvider<TestBean>
{}