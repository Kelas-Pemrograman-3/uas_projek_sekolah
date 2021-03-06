
<template>
  <q-page padding>
    <q-card>
      <q-table
        title="DATA SPP"
        :data="data"
        :columns="columns"
        row-key="id"
        :pagination.sync="pagination"
        :loading="loading"
        :filter="filter"
        @request="onRequest"
        binary-state-sort
      >
      <template v-slot:body="props">
          <q-tr :props="props">
            <q-td key="nis" :props="props">{{ props.row.nis }}</q-td>
            <q-td key="nama" :props="props">{{ props.row.nama }}</q-td>
            <q-td key="spp_bulan" :props="props">{{ props.row.spp_bulan }}</q-td>
            <q-td key="status" :props="props">{{ props.row.status }}</q-td>
                        <q-td key="aksi" :props="props">
              <q-card-actions align="around" class="row q-col-gutter-md no-wrap">
                <div class="col q-gutter-md">
                  <q-btn round @click="openDialog(props.row)" color="secondary" icon="edit"></q-btn>
                  <q-btn round @click="confirm(props.row._id)" color="negative" icon="delete"></q-btn>
                </div>
              </q-card-actions>
            </q-td>
          </q-tr>
        </template>

    </q-table>
    </q-card>
      <q-dialog v-model="openedit" v-if="openedit">
        <q-card style="width: 700px; max-width: 80vw;">
          <q-card-section class="row items-center">
            <div class="text-h6">Edit Data</div>
          </q-card-section>

          <q-separator/>

          <q-card-section style="max-height: 50vh" class="scroll">
            <q-input label="NIS" v-model="activedata.nis"></q-input>
            <q-input label="nama" v-model="activedata.nama"></q-input>
            <q-input label="spp_bulan" v-model="activedata.spp_bulan"></q-input>
            <q-input label="status" v-model="activedata.status"></q-input>
          </q-card-section>

          <q-card-actions align="right">
            <q-btn flat label="Cancel" color="primary" v-close-popup/>
            <q-btn flat label="Edit" color="primary" @click="edit()" />
          </q-card-actions>
        </q-card>
      </q-dialog>
  </q-page>
</template>

<script>
export default {
  name: 'PageIndex',
  data () {
    return {
      filter: '',
      loading: false,
      pagination: {
        sortBy: 'name',
        descending: false,
        page: 1,
        rowsPerPage: 3,
        rowsNumber: 10
      },
      columns: [
        {
          name: 'nis',
          required: true,
          label: 'NIS',
          align: 'left',
          field: 'nis',
          format: val => `${val}`,
          sortable: true
        },
        { name: 'nama', align: 'center', label: 'nama', field: 'nama', sortable: true },
        { name: 'spp_bulan', align: 'center', label: 'spp_bulan', field: 'spp_bulan', sortable: true },
        { name: 'status', align: 'center', label: 'status', field: 'status', sortable: true },
        { name: 'aksi', align: 'center', label: 'Action', field: 'aksi' }
      ],
      data: [],
      original: [],
      openedit: false,
      activedata: null
    }
  },
  created () {
    this.getData()
  },
  mounted () {
    this.onRequest({
      pagination: this.pagination,
      filter: undefined
    })
  },
  methods: {
    getData () {
      this.$axios.get('/spp/getallspp')
        .then(res => {
          console.log(res)
          this.original = res.data.data
        })
    },
    showNotif (msg, color) {
      this.$q.notify({
        message: msg,
        color: color
      })
    },
    onRequest (props) {
      let { page, rowsPerPage, rowsNumber, sortBy, descending } = props.pagination
      let filter = props.filter

      this.loading = true

      // emulate server
      setTimeout(() => {
        // update rowsCount with appropriate value
        this.pagination.rowsNumber = this.getRowsNumberCount(filter)

        // get all rows if "All" (0) is selected
        let fetchCount = rowsPerPage === 0 ? rowsNumber : rowsPerPage

        // calculate starting row of data
        let startRow = (page - 1) * rowsPerPage

        // fetch data from "server"
        let returnedData = this.fetchFromServer(startRow, fetchCount, filter, sortBy, descending)

        // clear out existing data and add new
        this.data.splice(0, this.data.length, ...returnedData)

        // don't forget to update local pagination object
        this.pagination.page = page
        this.pagination.rowsPerPage = rowsPerPage
        this.pagination.sortBy = sortBy
        this.pagination.descending = descending

        // ...and turn of loading indicator
        this.loading = false
      }, 1500)
    },
    fetchFromServer (startRow, count, filter, sortBy, descending) {
      let data = []

      if (!filter) {
        data = this.original.slice(startRow, startRow + count)
      } else {
        let found = 0
        for (let index = startRow, items = 0; index < this.original.length && items < count; ++index) {
          let row = this.original[index]
          // match filter?
          if (!row['name'].includes(filter)) {
            // get a different row, until one is found
            continue
          }
          ++found
          if (found >= startRow) {
            data.push(row)
            ++items
          }
        }
      }

      // handle sortBy
      if (sortBy) {
        data.sort((a, b) => {
          let x = descending ? b : a
          let y = descending ? a : b
          if (sortBy === 'desc') {
            // string sort
            return x[sortBy] > y[sortBy] ? 1 : x[sortBy] < y[sortBy] ? -1 : 0
          } else {
            // numeric sort
            return parseFloat(x[sortBy]) - parseFloat(y[sortBy])
          }
        })
      }

      return data
    },
    getRowsNumberCount (filter) {
      if (!filter) {
        return this.original.length
      }
      let count = 0
      this.original.forEach((treat) => {
        if (treat['name'].includes(filter)) {
          ++count
        }
      })
      return count
    },
    openDialog (data) {
      this.openedit = true
      this.activedata = data
    },
    edit () {
      this.$axios.put('/spp/upspp/' + this.activedata._id, this.activedata)
        .then(res => {
          if (res.data.error) {
            this.showNotif(res.data.pesan, 'negative')
          } else {
            this.showNotif(res.data.pesan, 'positive')
            this.openedit = false
            this.getData()
          }
        })
    },
    confirm (id) {
      this.$q.dialog({
        title: 'Confirm',
        message: 'Are you sure ?',
        cancel: true,
        persistent: true
      }).onOk(() => {
        this.$axios.delete('/spp/deletespp/' + id)
          .then(res => {
            if (res.data.error) {
              this.showNotif(res.data.pesan, 'negative')
            } else {
              this.showNotif(res.data.pesan, 'positive')
              this.getData()
            }
          })
      })
    }
  }
}
</script>
