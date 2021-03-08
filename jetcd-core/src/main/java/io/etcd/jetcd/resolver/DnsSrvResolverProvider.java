/*
 * Copyright 2016-2021 The jetcd authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.etcd.jetcd.resolver;

import java.net.URI;

import javax.annotation.Nullable;

import com.google.auto.service.AutoService;
import io.grpc.NameResolver;
import io.grpc.NameResolverProvider;

@AutoService(NameResolverProvider.class)
public class DnsSrvResolverProvider extends NameResolverProvider {
    @Override
    protected boolean isAvailable() {
        return true;
    }

    @Override
    protected int priority() {
        return 5;
    }

    @Override
    public String getDefaultScheme() {
        return DnsSrvNameResolver.SCHEME;
    }

    @Nullable
    @Override
    public NameResolver newNameResolver(URI targetUri, NameResolver.Args args) {
        return DnsSrvNameResolver.SCHEME.equals(targetUri.getScheme())
            ? new DnsSrvNameResolver(targetUri)
            : null;
    }
}
