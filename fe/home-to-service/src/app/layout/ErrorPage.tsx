import { Link } from "react-router-dom";
        import Footer from "./Footer";
        import LayoutNavbar from "./LayoutNavbar";

        export default function ErrorPage() {
        return (
<>
    <LayoutNavbar/>

    <div className="page-wrap d-flex flex-row align-items-center min-vh-100">
        <div className="container">
            <div className="row justify-content-center">
                <div className="col-md-12 text-center">
                    <span className="display-1 d-block text-danger">404</span>
                    <div className="mb-4 lead">
                        The page you are looking for was not found.
                    </div>
                    <Link to="/" className="btn btn-primary">
                        Back to Home
                    </Link>
                </div>
            </div>
        </div>
    </div>

    <Footer/>
</>
        );
        }
