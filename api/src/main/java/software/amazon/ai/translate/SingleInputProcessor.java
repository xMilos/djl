/*
 * Copyright 2019 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file except in compliance
 * with the License. A copy of the License is located at
 *
 * http://aws.amazon.com/apache2.0/
 *
 * or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES
 * OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package software.amazon.ai.translate;

import software.amazon.ai.ndarray.NDArray;
import software.amazon.ai.ndarray.NDList;

public abstract class SingleInputProcessor<I> implements PreProcessor<I> {

    private Transform transform;

    public SingleInputProcessor(Transform transform) {
        this.transform = transform;
    }

    @Override
    @SuppressWarnings("PMD.SignatureDeclareThrowsException")
    public NDList processInput(TranslatorContext ctx, I input) throws Exception {
        NDArray array = decode(ctx, input);
        array = transform.transform(array, true);
        return new NDList(array);
    }

    @SuppressWarnings("PMD.SignatureDeclareThrowsException")
    protected abstract NDArray decode(TranslatorContext ctx, I input) throws Exception;
}
