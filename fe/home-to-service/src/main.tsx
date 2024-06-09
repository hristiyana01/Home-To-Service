import React from "react";
        import ReactDOM from "react-dom/client";
        import "./app/layout/styles.css";
        import { StoreContext, store } from "./app/stores/stores";
        import { router } from "./app/router/Routes";
        import { RouterProvider } from "react-router-dom";
        import { CookiesProvider } from "react-cookie";

        ReactDOM.createRoot(document.getElementById("root")!).render(
<React.StrictMode>
    <StoreContext.Provider value={store}>
    <CookiesProvider>
        <RouterProvider router={router} />
    </CookiesProvider>
</StoreContext.Provider>
        </React.StrictMode>
        );
