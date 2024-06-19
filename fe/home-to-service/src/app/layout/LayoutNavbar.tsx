import { Navbar, Nav, Container } from "react-bootstrap";
import { useStore } from "../stores/stores";
import { useCookies } from "react-cookie";
import { useNavigate } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faHeart, faUser } from "@fortawesome/free-regular-svg-icons";

export default function LayoutNavbar() {
  const navigation = useNavigate();
  const { userStore } = useStore();
  const [cookies, setCookie, removeCookie] = useCookies(["auth"]);

  const logout = () => {
    userStore.removeToken();
    removeCookie("auth");
    navigation("/");
    window.location.reload();
  };

  return (
    <Navbar bg="light" expand="lg">
      <Container>
        <img
          src="/site-logo.jpg"
          width="32"
          height="32"
          className="d-inline-block align-top rounded mx-2"
          alt="Logo"
        />
        <Navbar.Brand className="user-select-none">
          <Nav.Link href="/">Home To Service</Nav.Link>
        </Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="me-auto">
            <Nav.Link href="/posts/all">All Posts</Nav.Link>
            <Nav.Link href="/categories">Categories</Nav.Link>
            <Nav.Link href="/about">About</Nav.Link>
            <Nav.Link href="/contacts">Contact</Nav.Link>
          </Nav>
          <Nav className="right-side-navbar">
            {userStore.isLoggedIn ? (
              <>
                <button
                  className="btn btn-outline-success mx-2"
                  onClick={() => navigation("/posts/create")}
                >
                  Create Post
                </button>

                <button
                  onClick={logout}
                  className="btn btn-outline-danger mx-2"
                >
                  Logout
                </button>

                <Nav.Link className="mx-2" href="/profile">
                  <FontAwesomeIcon icon={faUser} />
                </Nav.Link>
                <Nav.Link className="mx-2" href="/user/favorites">
                  <FontAwesomeIcon icon={faHeart} />
                </Nav.Link>
              </>
            ) : (
              <>
                <Nav.Link href="/user/login">Login</Nav.Link>
                <Nav.Link href="/user/register">Register</Nav.Link>
              </>
            )}
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}
