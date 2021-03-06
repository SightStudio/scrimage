/*
   Copyright 2013 Stephen K Samuel

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package com.sksamuel.scrimage.filter;

import java.awt.image.BufferedImageOp;

public class GammaFilter extends BufferedOpFilter {

    private final double gamma;

    public GammaFilter(double gamma) {
        this.gamma = gamma;
    }

    public GammaFilter() {
        this(1.0f);
    }

    @Override
    public BufferedImageOp op() {
        thirdparty.jhlabs.image.GammaFilter op = new thirdparty.jhlabs.image.GammaFilter();
        op.setGamma((float) gamma);
        return op;
    }
}
