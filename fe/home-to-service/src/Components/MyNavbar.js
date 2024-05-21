import { Navbar, Nav, Container } from 'react-bootstrap';
import ContactsPage from "./ContactsPage";

function MyNavbar() {

  return (
    <Navbar bg="light" expand="lg">
      <Container>
        <Navbar.Brand href="index.html">Home</Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="me-auto">
            {/*<Nav.Link href="/categories-list" component={CategoriesList}>Categories</Nav.Link>*/}
            <Nav.Link href="/all-posts">All Posts</Nav.Link>
            <Nav.Link href="/categories-list">Categories</Nav.Link>
            <Nav.Link href="#about">About</Nav.Link>
            <Nav.Link href="/contacts" component={ContactsPage}>Contact</Nav.Link>
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}

export default MyNavbar;