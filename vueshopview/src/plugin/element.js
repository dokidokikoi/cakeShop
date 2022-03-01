import Vue from 'vue'
import { Header,
        Menu,
        MenuItem,
        Submenu,
        Main,
        Footer,
        Container,
        Button,
        Input,
        Card,
        Row,
        Col,
        Image,
        Avatar,
        Pagination,
        Tag,
        Timeline,
        TimelineItem,
        Breadcrumb,
        BreadcrumbItem,
        Form,
        FormItem,
        MessageBox,
        Message,
        Carousel,
        CarouselItem,
        Dropdown,
        DropdownItem,
        DropdownMenu,
        Badge,
        Table,
        TableColumn,
        Aside,
        Select,
        Option,
        Upload
 } from 'element-ui'

Vue.use(Header)
Vue.use(Menu)
Vue.use(MenuItem)
Vue.use(Submenu)
Vue.use(Main)
Vue.use(Footer)
Vue.use(Button)
Vue.use(Container)
Vue.use(Input)
Vue.use(Card)
Vue.use(Row)
Vue.use(Col)
Vue.use(Image)
Vue.use(Avatar)
Vue.use(Pagination)
Vue.use(Tag)
Vue.use(Timeline)
Vue.use(TimelineItem)
Vue.use(Breadcrumb)
Vue.use(BreadcrumbItem)
Vue.use(Form)
Vue.use(FormItem)
Vue.use(Carousel)
Vue.use(CarouselItem)
Vue.use(Dropdown)
Vue.use(DropdownItem)
Vue.use(DropdownMenu)
Vue.use(Badge)
Vue.use(Table)
Vue.use(TableColumn)
Vue.use(Aside)
Vue.use(Select)
Vue.use(Option)
Vue.use(Upload)

Vue.prototype.$message = Message
Vue.prototype.$confirm = MessageBox.confirm
Vue.prototype.$alert = MessageBox.alert

