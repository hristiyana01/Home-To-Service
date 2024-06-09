import { Toaster } from "react-hot-toast";
        import { Outlet } from "react-router-dom";
        import LayoutNavbar from "./LayoutNavbar";
        import Footer from "./Footer";
        import { observer } from "mobx-react-lite";

        export default observer(function App() {
        return (
<main>
    <LayoutNavbar/>
    <Toaster position="top-center" reverseOrder={false} />
    <div className="min-vh-100">
        <Outlet/>
    </div>
    <Footer/>
</main>
        );
        });
