import React, { useEffect, useState } from 'react';
import { Route, Switch } from 'react-router-dom';

import {
    LOGIN_ROUTE,
    MAIN_ROUTE,
    ROOT_ROUTE,
    SIGNUP_ROUTE,
} from './constants';
import {validateToken} from './utils';
import Error from './pages/Error';
import Info from './pages/Info';
import Login from './pages/Login';
import Main from './pages/main/Main';
import SignUp from './pages/SignUp';

function RoutesContainer() {
    const [canRender, setCanRender] = useState(false);

    useEffect(() => {
        (async function () {
            try {
                await validateToken();
            } finally {
                setCanRender(true);
            }
        })();
    }, []);

    if (!canRender) {
        return 'Loading...';
    }

    return (
        <main>
            <Switch>
                <Route path={ROOT_ROUTE} exact component={Info} />
                <Route path={LOGIN_ROUTE} exact component={Login} />
                <Route path={SIGNUP_ROUTE} exact component={SignUp} />
                <Route path={MAIN_ROUTE} component={Main} />
                <Route component={Error} />
            </Switch>
        </main>
    )
}

export default RoutesContainer;